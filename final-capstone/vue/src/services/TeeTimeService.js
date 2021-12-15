import axios from 'axios';

export default {
    getTeeTimes() {
        return axios.get(`/teetimes`);
    },

    addTeeTime(teeTime) {
        return axios.post(`/create-teetime/${teeTime}`);
    }
}