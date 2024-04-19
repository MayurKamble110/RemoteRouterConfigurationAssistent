import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css'; 
import toastr from 'toastr';
import 'toastr/build/toastr.min.css';

  export const LoginSuccessToast = ( message) => {
    Swal.fire({
      icon: 'success',
      text: message,
      color: 'black',
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
  };
  
  export const LoginErrorToast = (message) => {
    Swal.fire({
      icon: 'error',
      text: message,
      color: 'black',
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
  };
  
  export const NotifySuccessToast = (message) => {
    toastr.success(message);
  }

  export const NotifyErrorToast = (message) => {
    toastr.error(message);
  }