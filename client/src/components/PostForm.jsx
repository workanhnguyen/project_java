import React, { useRef } from "react";

import CloseIcon from "@mui/icons-material/Close";
import PublicIcon from "@mui/icons-material/Public";
import { Avatar, Button, Divider } from "@mui/material";

import { blankAvatar } from "../assets";
import ImageUploader from "./ImageUploader";
import DivTextField from "./DivTextField";
const PostForm = ({ show, setShow }) => {
  const postPanelRef = useRef();
  
  const handleClosePostPanel = () => {
    setShow(false);
  };

  return (
    <div
      ref={postPanelRef}
      className={`${
        show ? "" : "hidden"
      } w-full h-screen fixed flex justify-center items-center top-0 left-0 bg-blackOverlay`}
    >
      <div className="relative max-sm:w-full w-128 p-4 bg-white rounded-md">
        <div
          onClick={handleClosePostPanel}
          className="absolute top-2.5 right-3 p-2 rounded-full bg-gray cursor-pointer hover:bg-gray-2"
        >
          <CloseIcon />
        </div>
        <div className="w-full flex justify-center items-center mb-4">
          <p className="font-bold text-lg">Tạo bài viết</p>
        </div>
        <Divider />
        <div className="w-full flex items-center mt-3">
          <Avatar
            src={blankAvatar}
            alt="avatar"
            sx={{ width: 40, height: 40 }}
          />
          <div className="flex-1 ml-2">
            <p className="font-semibold">Anh Nguyễn</p>
            <div className="w-fit flex items-center py-1 px-2 bg-gray-2 rounded-md">
              <PublicIcon sx={{ width: 12, height: 12 }} />
              <span className="ml-2 text-xs font-semibold">Công khai</span>
            </div>
          </div>
        </div>
        <DivTextField />
        <ImageUploader />
        <div className="mt-3">
          <Button fullWidth disableElevation variant="contained">
            Đăng
          </Button>
        </div>
      </div>
    </div>
  );
};

export default PostForm;