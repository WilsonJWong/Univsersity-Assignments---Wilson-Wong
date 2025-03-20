<script setup>
import BHeader from './components/BHeader.vue'
import NavBar from './components/NavBar.vue'
</script>

<template>
  <div class="main-controller">
    <header>
      <BHeader />
    </header>

    <!-- Navigation Bar -->
    <NavBar />

    <main class="main-box d-flex justify-content-center text-center mt-5">
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
