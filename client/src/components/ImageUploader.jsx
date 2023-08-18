import React, { useRef, useState } from 'react'

import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import CloseIcon from "@mui/icons-material/Close";

const ImageUploader = () => {
    
  const [images, setImages] = useState([]);
  const fileInputRef = useRef();
    const handleFileChange = (e) => {
        const files = Array.from(e.target.files);
        setImages([...images, ...files]);
      };
    
      const handleRemoveImage = (indexToRemove) => {
        setImages((prevImages) =>
          prevImages.filter((_, index) => index !== indexToRemove)
        );
      };
  return (
    <div className="w-full h-60 border mt-3 p-2 border-gray-2 rounded-md">
          {images.length === 0 ? (
            <label htmlFor="fileInput" className={`relative overflow-auto`}>
              <div className="w-full h-full flex flex-col justify-center items-center bg-gray-3 rounded-md hover:bg-gray-2 cursor-pointer">
                <AddPhotoAlternateIcon fontSize="large" />
                <span className="font-semibold">Thêm ảnh</span>
                <span className="text-xs text-dark-gray">hoặc kéo và thả</span>
              </div>
            </label>
          ) : (
            <div className="relative w-full h-full bg-gray-3 overflow-auto rounded-md">
              <label
                htmlFor="fileInput"
                className="absolute z-10 top-2.5 left-3 flex items-center px-2 py-1.5 rounded-md bg-white cursor-pointer hover:bg-gray-2"
              >
                <AddPhotoAlternateIcon fontSize="small" />
                <span className="text-sm ml-1">Thêm ảnh</span>
              </label>
              {images.map((image, index) => (
                <div key={index} className="relative mb-3">
                  <div
                    onClick={() => handleRemoveImage(index)}
                    className="absolute top-2.5 right-3 px-1.5 py-1 rounded-full bg-white cursor-pointer hover:bg-gray-2"
                  >
                    <CloseIcon fontSize="small" />
                  </div>
                  <img
                    className="h-full"
                    src={URL.createObjectURL(image)}
                    alt={`Preview ${index}`}
                  />
                </div>
              ))}
            </div>
          )}
          <input
            className="hidden"
            ref={fileInputRef}
            id="fileInput"
            type="file"
            multiple
            accept=".png, .jpg"
            onChange={handleFileChange}
          />
        </div>
  )
}

export default ImageUploader