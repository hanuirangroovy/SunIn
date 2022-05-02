import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

/**
 * @param message Toast 표시할 메세지
 */

const ToastMessage = (message: any) => {
  toast.success(message, {
    theme: "colored",
    position: "top-right",
    autoClose: 2000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
  });
};

export default ToastMessage;
