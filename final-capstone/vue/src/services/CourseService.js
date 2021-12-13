import axios from 'axios';

export default {
    addCourse(newCourse) {
        return axios.post('/add-course', newCourse);
    },
    getCourses() {
        return axios.get(`/courses`);
    },
    getCourseById(courseId) {
        return axios.get(`/course/id/${courseId}`)
    }
}
