import axios from 'axios';

export default {
    addScore(newScore) {
        return axios.post(`/teetimes/add-score`, newScore);
    },
    getScore() {
        return axios.get(`/teetimes/score`)
    },
    getScoreByUserId(userId) {
        return axios.get(`/teetimes/score/user/${userId}`)
    },
    getScoreByTeeTimeId(teeTimeId) {
        return axios.get(`/teetimes/score/${teeTimeId}`)
    },
    addScoreToUser(teeTimeId, userId) {
        return axios.post(`/teetimes/${teeTimeId}/addScore/user/${userId}`)
    }
}
