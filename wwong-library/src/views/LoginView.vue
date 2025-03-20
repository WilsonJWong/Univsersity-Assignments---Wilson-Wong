<template>
  <div class="container mt-5">
    <h1 class="text-center">Login</h1>
    <form @submit.prevent="submitLogin">
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="text" id="username" class="form-control" v-model="username" required />
      </div>

      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" id="password" class="form-control" v-model="password" required />
      </div>

      <div v-if="loginError" class="text-danger">
        <p>Invalid username or password.</p>
      </div>

      <div class="text-center">
        <button type="submit" class="btn btn-primary">Login</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const loginError = ref(false)

const hardcodedUsername = 'user123'
const hardcodedPassword = 'password123'

const router = useRouter()

const submitLogin = () => {
  if (username.value === hardcodedUsername && password.value === hardcodedPassword) {
    localStorage.setItem('isAuthenticated', 'true')
    loginError.value = false
    location.reload()
    window.location.replace('/about')
  } else {
    loginError.value = true
  }
}
</script>

<style scoped>
.container {
  max-width: 400px;
  margin: 0 auto;
  padding-top: 50px;
}

.text-center {
  text-align: center;
}
</style>
