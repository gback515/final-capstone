import axios from 'axios';

export default {
    getTeeTimes() {
        return axios.get(`/tee-time`);
    },
    addTeeTime(teeTimeId) {
        return axios.post(`/create-tee-time/${teeTimeId}`);
    },
    getTeeTimeByUser(userId) {
        return axios.get(`/tee-time/${userId}`);
    },
    addScoreToTeeTime(teeTimeId, score) {
        return axios.post(`/tee-time/${teeTimeId}/score/${score}`)
    }
}