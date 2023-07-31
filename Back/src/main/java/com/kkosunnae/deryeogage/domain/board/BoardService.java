package com.kkosunnae.deryeogage.domain.board;

import com.kkosunnae.deryeogage.domain.common.DetailCodeRepository;
import com.kkosunnae.deryeogage.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final DetailCodeRepository detailCodeRepository;
    private final JjimRepository jjimRepository;
    private final BoardFileRepository boardFileRepository;

    //게시글 작성
    @Transactional
    public int save(BoardDto boardDto) {
        log.info("게시글 제목 : ", boardDto.getTitle());
        boardDto.setCreatedDate(LocalDateTime.now());
        BoardEntity board = boardRepository.save(boardDto.toEntity(userRepository, detailCodeRepository));
        return board.getId();
    }


    //게시글 수정
    @Transactional
    public int update(Integer id, BoardDto boardDto) {
        BoardEntity board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저의 게시글이 없습니다. id : " + id));
        boardDto.setCreatedDate(LocalDateTime.now());
        board.update(boardDto);
        boardRepository.save(board);
        return board.getId();
    }

    //게시글 삭제
    @Transactional
    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }

    //게시글 상세 조회
    @Transactional(readOnly = true)
    public BoardDto getBoard(Integer id) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
        return board.toDto();
    }

    //전체 게시글 목록 조회
    @Transactional
    public Page<BoardDto> findAll(final Pageable pageable) {
        Page<BoardEntity> boardPage = boardRepository.findAll(pageable);
        return boardPage.map(BoardEntity::toDto);
    }

    //게시글 찜
    @Transactional
    public int like(JjimDto jjimDto) {

        if (!jjimRepository.existsByUserIdAndBoardId(jjimDto.getUserId(), jjimDto.getBoardId())) {
            JjimEntity jjim = jjimRepository.save(jjimDto.toEntity(boardRepository, userRepository));
            return jjim.getId();
        }//만약 이미 찜한 게시글이라면
        else throw new IllegalArgumentException("이미 찜한 게시판입니다.");
    }

    //게시글 찜취소
    @Transactional
    public void unlike(Long userId, Integer boardId) {
        jjimRepository.deleteByUserIdAndBoardId(userId, boardId);
    }


    //게시글에 저장된 파일 저장
    public void saveBoardFile(Integer boardId, Map<String, List> nameList) {

        LocalDateTime uploadTime = LocalDateTime.now();

        List<String> originalNames = nameList.get("original");
        List<String> savedNames = nameList.get("saved");
        List<String> savedPaths = nameList.get("path");

        // Dto에 담기: IntStream을 사용해서 index 기반으로 처리
        IntStream.range(0, originalNames.size())
                .forEach(i -> {
                    BoardFileDto boardFileDto = new BoardFileDto();

                    boardFileDto.setBoardId(boardId);
                    boardFileDto.setOriginalName(originalNames.get(i));
                    boardFileDto.setSavedName(savedNames.get(i));
                    String fileType = savedNames.get(i).split("\\.")[1];

                    if (fileType.equals("mp4")) { //동영상
                        boardFileDto.setType(true);
                    } else { //이미지
                        boardFileDto.setType(false);
                    }

                    boardFileDto.setPath(savedPaths.get(i));
                    boardFileDto.setCreatedDate(uploadTime);

                    // 그 다음 Entity로 변환하여 DB에 저장
                    boardFileRepository.save(boardFileDto.toEntity(boardRepository));
                });
    }

}