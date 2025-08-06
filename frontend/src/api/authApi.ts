import axiosClient from "./axiosClient";
import { ENDPOINTS } from "./endpoints";
import type {LoginDto}  from "../dto/auth/loginDto";


const authApi = {
    login: (payload : LoginDto) => axiosClient.post(ENDPOINTS.AUTH.LOGIN, payload),

    logout: () => axiosClient.post(ENDPOINTS.AUTH.LOGOUT)
}

export default authApi;