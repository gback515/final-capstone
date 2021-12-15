import axios from 'axios';

export default {
    addScore(newScore) {
        return axios.post(`/teetimes/${teeTimeId}/add-score`, newScore);
    },
    addScoreToUser(scoreId, userId) {
        return axios.post(`/score/${scoreId}/user/${userId}`)
    }

}