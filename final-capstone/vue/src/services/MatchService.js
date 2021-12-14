import axios from 'axios';

export default {
    getAllMatches(leagueId) {
        return axios.get(`/league/${leagueId}`)
    }
}