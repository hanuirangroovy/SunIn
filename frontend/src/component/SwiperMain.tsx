import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation, Pagination, Autoplay } from "swiper";
import { Image } from "semantic-ui-react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

const SwiperMedia = ({ media }: any) => {
  SwiperCore.use([Navigation, Pagination, Autoplay]);
  return (
    <>
      <Swiper
        style={{ width: "auto", height: "auto" }} // 이미지 height
        spaceBetween={0} // 이미지 사이 간격???
        slidesPerView={1} // 한 슬라이드에 띄울 이미지 기본 1
        navigation
        pagination={{ clickable: true }}
        loop={true}
        autoplay={{ delay: 3000 }} // 다음이미지 넘어가는 시간 카운트
      >
        {media.map((item: any, index: any) => {
          return (
            <SwiperSlide key={index}>
              <Image src={item} alt={item} />
            </SwiperSlide>
          );
        })}
      </Swiper>
    </>
  );
};

export default SwiperMedia;
