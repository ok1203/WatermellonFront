import { createApp } from 'vue'
import App from './App.vue'


createApp(App).mount('#app');


import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

const app = createApp(App);

app.component('DatePicker', Datepicker);

app.mount('#app');