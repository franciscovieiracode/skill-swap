import axiosClient from "./axiosClient";
import { ENDPOINTS } from "./endpoints";

const userApi = {
  getCurrentUser: () => 
    axiosClient.get(ENDPOINTS.USERS.GETCURRENTUSER)
  
};


export default userApi;