import axios from 'axios';

export default {
    getTeeTimes() {
        return axios.get(`/tee-time`);
    },
    addTeeTime(teeTime) {
        return axios.post(`/create-tee-time/${teeTime}`);
    },
    getTeeTimeByUser(userId) {
        return axios.get(`/tee-time/${userId}`);
    }
}