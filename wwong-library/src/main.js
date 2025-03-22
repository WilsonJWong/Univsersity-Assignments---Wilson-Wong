import 'bootstrap/dist/css/bootstrap.min.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import PrimeVue from 'primevue/config'
import Aura from '@primevue/themes/aura'

import { initializeApp } from 'firebase/app'
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

const firebaseConfig = {
  apiKey: 'AIzaSyD-oYRgTsDcsxfbHnZZXM8scO1fsqBp2Gs',
  authDomain: 'week-4---wilson.firebaseapp.com',
  projectId: 'week-4---wilson',
  storageBucket: 'week-4---wilson.firebasestorage.app',
  messagingSenderId: '866195190393',
  appId: '1:866195190393:web:63f08adb50fc14057a1aa8',
}

initializeApp(firebaseConfig)

const app = createApp(App)
app.use(PrimeVue, { theme: { preset: Aura } })
app.use(router)

app.mount('#app')
