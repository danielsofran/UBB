import {Button, Carousel, Container, Modal} from "react-bootstrap";
import React, {CSSProperties, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

interface AppCarouselProps {
  editable?: boolean;
  sources: string[];
  setSources: (sources: string[]) => void;
  imageStyle?: CSSProperties;
}

export default function AppCarousel(props: AppCarouselProps) {
  const {sources, setSources, editable, imageStyle} = props;

  const [carouselKey, setCarouselKey] = useState<number>(0);
  const [showModal, setShowModal] = useState<boolean>(false);

  const carouselCSS: CSSProperties = {
    width: '100%',
  }

  const imageCSS: CSSProperties = {
    ...imageStyle,
    height: imageStyle?.height || '300px',
    objectFit: imageStyle?.objectFit || 'cover',
    objectPosition: imageStyle?.objectPosition || 'center',
    borderRadius: imageStyle?.borderRadius || 'none'
  }

  const deleteButtonCSS: CSSProperties = {
    position: 'absolute',
    bottom: '20px',
    left: 'calc(50% - 20px)',
  }

  const deleteSource = (e: any, src: string) => {
    e.stopPropagation()
    setSources(sources.filter(s => s !== src))
    setCarouselKey(carouselKey + 1)
  }

  const RawCarousel = (inModal?: boolean) => {
    const imageCSSOverride: CSSProperties = inModal ? {...imageCSS, height: '85vh', width: 'auto'} : imageCSS
    return (
      <Container fluid>
        <Carousel variant="dark" style={carouselCSS} pause="hover" touch key={carouselKey}>
          {sources.map((src, index) =>
            <Carousel.Item key={index} style={{position: "relative"}}>
              <img
                className="d-block mx-auto"
                src={src}
                alt={`Slide ${index}`}
                style={imageCSSOverride}
                onClick={() => inModal ? window.open(src, '_blank') : setShowModal(true)}
              />
              {!inModal && editable && (
                <Carousel.Caption>
                  <Button variant="danger" style={deleteButtonCSS} onClick={(e) => deleteSource(e, src)}>
                    <FontAwesomeIcon icon={faTrash}/>
                  </Button>
                </Carousel.Caption>
              )}
            </Carousel.Item>
          )}
        </Carousel>
      </Container>
    )
  }

  return <>
    <Modal show={showModal} fullscreen={true} onHide={() => setShowModal(false)}>
      <Modal.Header closeButton>
        <Modal.Title>Preview images</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {RawCarousel(true)}
      </Modal.Body>
    </Modal>
    {RawCarousel(false)}
  </>
}