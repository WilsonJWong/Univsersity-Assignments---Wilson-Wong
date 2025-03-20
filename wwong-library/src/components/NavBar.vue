<template>
  <nav class="navbar">
    <ul>
      <li v-if="!isAuthenticated"><router-link to="/">Home</router-link></li>
      <li v-if="isAuthenticated"><router-link to="/about">About</router-link></li>
      <li v-if="!isAuthenticated"><router-link to="/login">Login</router-link></li>
      <li v-if="isAuthenticated">
        <button @click="logout" class="logout-btn">Logout</button>
      </li>
    </ul>
  </nav>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const isAuthenticated = computed(() => localStorage.getItem('isAuthenticated') === 'true')

const logout = () => {
  localStorage.removeItem('isAuthenticated')
  location.reload()
  window.location.replace('/login')
}
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #333;
  padding: 1rem;
}

ul {
  list-style: none;
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin: 0;
  padding: 0;
}

li {
  color: white;
}

a {
  color: white;
  text-decoration: none;
}

.logout-btn {
  background: red;
  border: none;
  color: white;
  cursor: pointer;
}
</style>
