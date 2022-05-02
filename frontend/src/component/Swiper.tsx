import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation, Pagination, Autoplay } from "swiper";
import { Image, Modal } from "semantic-ui-react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";
import { useState } from "react";

const SwiperMedia = ({ media }: any) => {
  SwiperCore.use([Navigation, Pagination, Autoplay]);

  const [open, setOpen] = useState(false)
  const [showImage, setShowImage] = useState("")
  
  function openModal() {
    setOpen(!open)
  }
  
  return (
    <>
      <Swiper
        onClick={openModal}
        spaceBetween={10}
        slidesPerView={1.6}
        navigation
        speed={500}
        pagination={{ clickable: true }}
        loop={true}
        autoplay={{ delay: 3000 }}
        centeredSlides={true}>
        {media.map((item: any, index: any) => {
          return (
            <SwiperSlide key={index}>
              <Image src={item} alt={item} onClick={(() => {setShowImage(item);})}/> 
            </SwiperSlide>
          );
        })}
      </Swiper>

      <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
    >
      <Modal.Content image style={{ backgroundColor: "dimgrey"}}>
        <Image src={showImage} alt="" style={{margin: "auto"}} fluid/>
      </Modal.Content>
    </Modal>
    </>
  );
};

export default SwiperMedia;
