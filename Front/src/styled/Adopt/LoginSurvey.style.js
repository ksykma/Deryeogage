import styled from "styled-components";

export const Span = styled.span`
  //margin-left: 7.5%;
  color: rgba(255, 145, 77, 1);
`;

export const Smallspacer = styled.div`
height: 20px;
`;

export const CarouselContainer = styled.div`
  /* display: flex; */
  background-color: #FFF7E7;
  border: 1px #ff914d none;
  justify-content: center;
  padding-top: 20px;
  padding-bottom: 50px;
  padding-left: 20px;
  padding-right: 20px;
  margin-top: 1%;
  margin-bottom: 3%;
  border-radius: 30px;
`;

export const CustomCarousel = styled.div`

  /* 좌우 버튼 색 변경 */
  .carousel-control-prev-icon,
  .carousel-control-next-icon {
    display: none;
    //filter: invert(1);  /* 색상 반전 */
    //background-color: #ff914d;  /* 활성화된 버튼 */
  }

  /* 좌우 버튼 위치 */
  .carousel-control-prev {
    /* left: -15%;  */
     /* 기본값은 0% */
  }
  .carousel-control-next {
    /* right: -15%; */
     /* 기본값은 0% */
  }

  /* 하단 5개 버튼 위치 */
  .carousel-indicators {
    position: relative;
    bottom: -360px;
}

  /* 순서를 나타내는 바(인디케이터) 색 변경 */
  .carousel-indicators button {
    background-color: #ff914d;  /* 기본 버튼 */
  }
  .carousel-indicators .active {
    background-color: #4A2511;  /* 활성화된 버튼 */
  }
`;

export const MediaAndCaptionContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
`;

export const MediaContainer = styled.div`
  width: 25%;
  height: 290px;
  overflow: hidden;
  z-index: 1000; // 다른 요소보다 위에 위치하기 위해 높은 값 지정
`;

export const StyledMedia = styled.img`
  width: 100%;          /* 이미지를 컨테이너 너비에 맞춤 */
  height: 100%;         /* 이미지를 컨테이너 높이에 맞춤 */
  object-fit: cover;  
  object-position: center; /* 이미지의 중앙 부분이 중점이 되도록 설정 */  
  border-radius: 10px;
`;

export const CaptionContainer = styled.div`
  width: 20%;
  padding-top: 5vh;
  margin-left: 20px; 
  margin-right: 10px; 
  height: 300px;
  background-color: #FFF7E7;
`;

export const StyledText = styled.p`
  font-size: 1.3vw;     // 글자 크기 지정. 원하는 크기로 조절 가능.
  margin-bottom: 1vw;   // 아래쪽 간격 지정. 원하는 크기로 조절 가능.
  margin-left: 1vw;
`;

export const StyledTextTitle = styled.p`
  font-size: 1.5vw;     // 글자 크기 지정. 원하는 크기로 조절 가능.
  margin-bottom: 1vw;   // 아래쪽 간격 지정. 원하는 크기로 조절 가능.
  margin-left: 1vw;
`;

export const Box = styled.div`
  width: 25%;
  margin-left: 15px; 
  padding: 1vh;
  border: 1px #ff914d solid;
  border-radius: 30px;
  background-color: white;
  text-align: center;
  display: flex;
  align-items: center; /* SurveyPaw 컴포넌트들을 수직 방향으로 중앙 정렬 */
  flex-direction: column; /* SurveyPaw 컴포넌트들을 수직 방향으로 배치 */
  /* margin-right: 1vw; */
`;

export const Result = styled.div`
  margin-right: 1vw;
  margin-bottom: 1vw;
`

export const pTag = styled.p`
  font-weight: bold;
`