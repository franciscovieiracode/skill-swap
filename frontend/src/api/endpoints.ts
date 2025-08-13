export const ENDPOINTS = {
    AUTH: {
        LOGIN : "/api/v1/auth/login",
        SIGNUP: "/api/v1/auth/signup",
        LOGOUT: "/api/v1/auth/logout"
    },
    USERS:{
        GETCURRENTUSER:"/api/v1/users/getCurrentUser"
    },
    DASHBOARD : {
        GETALLOFFEREDSKILLS : "/api/v1/userOfferedSkills/getAllOfferedSkills",
        GETALLCATEGORIES : "/api/v1/category/getAllCategories",
        GETSKILLBYID : "/api/v1/userOfferedSkills/getOfferedSkillById/"
    }
}