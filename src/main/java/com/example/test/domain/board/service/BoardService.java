package com.example.test.domain.board.service;

import com.example.test.domain.board.controller.dto.request.CreatePostRequestDTO;
import com.example.test.domain.board.controller.dto.request.UpdatePostRequestDTO;
import com.example.test.domain.board.controller.dto.response.PostResponseDTO;
import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.model.PostTag;
import com.example.test.domain.board.model.Tag;
import com.example.test.domain.board.repository.BoardDefRepository;
import com.example.test.domain.board.repository.PostRepository;
import com.example.test.domain.tag.repository.PostTagRepository;
import com.example.test.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardDefRepository boardDefRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    String COMMON_LOG = "api/board Service : ";

    // 저장
    @Transactional
    public PostResponseDTO insertPost(CreatePostRequestDTO postRequestDTO) {
        Post post = postRepository.save(dtoToEntity(postRequestDTO));
        log.info(COMMON_LOG + "saved post dto - {}", post);


        List<String> tagName = postRequestDTO.getTagName();
        if (!tagName.isEmpty()) {
            BoardDef boardDef = BoardDefService.boardDefFrom(postRequestDTO.getBoardCd());
            for (String tag : tagName) {
                Tag tagEntity = Tag.builder()
                        .tag(tag)
                        .boardCd(boardDef)
                        .build();
                tagRepository.save(tagEntity);

                PostTag po = PostTag.builder()
                        .tagNo(tagEntity)
                        .boardCd(boardDef)
                        .postNo(post)
                        .build();

                postTagRepository.save(po);
                post.addPostTag(po);

            }
        }

        return PostResponseDTO.from(post);
    }

    private Post dtoToEntity(CreatePostRequestDTO dto) {
        BoardDef boardDef = BoardDefService.boardDefFrom(dto.getBoardCd());
        log.debug(COMMON_LOG + "dtoToEntity boardDef - {} ", boardDef);

        return Post.builder()
                .boardCd(boardDef)
                .postSj(dto.getPostSj())
                .postCn(dto.getPostCn())
                .regstrId(dto.getRegstrId())
                .build();
    }

    // 수정
    public PostResponseDTO update(Long postNo, UpdatePostRequestDTO updatedPostDto) {
        Post foundPost = returnPost(postNo);
        setPost(foundPost, updatedPostDto);

        return PostResponseDTO.from(foundPost);
    }

    private void setPost(Post p, UpdatePostRequestDTO dto) {
        p.setPostSj(dto.getPostSj());
        p.setPostCn(dto.getPostCn());

        log.info(COMMON_LOG + "updated post dto - {}", p);
    }

    // 삭제
    public boolean delete(Long postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("삭제에 실패했습니다.");
            return false;
        }
    }

    // 전체 조회
    public List<PostResponseDTO> getListAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());
    }

    // 단일 조회
    public PostResponseDTO getPost(Long postId) {
        return PostResponseDTO.from(returnPost(postId));
    }

    public Post returnPost(Long postNo) {
        return postRepository.findById(postNo)
                .orElseThrow(() -> new NoSuchElementException("게시물"));
    }
}
