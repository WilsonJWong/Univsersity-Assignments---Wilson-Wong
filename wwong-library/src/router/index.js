import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import LoginView from '../views/LoginView.vue'
import DeniedView from '../views/AccessDenied.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/about',
    name: 'About',
    component: AboutView,
    beforeEnter: (to, from, next) => {
      const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true'
      if (isAuthenticated) {
        next()
      } else {
        next({ name: 'Login' }) // Redirect to Login page instead
      }
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/accessDenied',
    name: 'AccessDenied',
    component: DeniedView,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
