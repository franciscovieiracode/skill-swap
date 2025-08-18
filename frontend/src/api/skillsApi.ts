import type { MakeRequestDto } from "../dto/skills/MakeRequestDto";
import axiosClient from "./axiosClient";
import { ENDPOINTS } from "./endpoints";

const skillsApi = {

    makeRequest:(payload : MakeRequestDto) => axiosClient.post(ENDPOINTS.SKILLS.MAKEREQUESTSKILL, payload)

}

export default skillsApi;