<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue'
import GlobalFooter from '@/components/GlobalFooter.vue'
</script>

<template>
  <a-layout class="basic-layout">
    <a-layout-header class="layout-header">
      <GlobalHeader />
    </a-layout-header>
    <a-layout-content class="layout-content">
      <div class="content-wrapper">
        <RouterView />
      </div>
    </a-layout-content>
    <a-layout-footer class="layout-footer">
      <GlobalFooter />
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
.basic-layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: var(--layout-background);
}

.layout-header {
  height: var(--header-height);
  padding: 0;
  line-height: normal;
  background: var(--header-background);
}

.layout-content {
  flex: 1;
  padding: var(--content-padding) 0;
}

.content-wrapper {
  max-width: var(--content-max-width);
  margin: 0 auto;
  padding: 0 24px;
}

.layout-footer {
  padding: 0;
  background: transparent;
}
</style>
