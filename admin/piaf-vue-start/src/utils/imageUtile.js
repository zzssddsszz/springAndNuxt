export const toThumbnail = (image) => {

  if (image.img != null) {
    let img = image.img[0].location;
    if (image.thumbnailCreated) {
      return img.replace(".jpg", ".thumbnail.jpg")
    } else {
      return img;
    }
  }else {
    return "https://modoodesigner.speedgabia.com/test.jpg"
  }


}
