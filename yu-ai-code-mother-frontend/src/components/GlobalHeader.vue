<script setup lang="ts">
import { computed, h, type Component } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { HomeOutlined, InfoCircleOutlined } from '@ant-design/icons-vue'
import logoSvg from '@/assets/logo.svg'

interface MenuItemConfig {
  path: string
  name: string
  icon?: Component
}

const menuItems: MenuItemConfig[] = [
  { path: '/', name: 'Home', icon: HomeOutlined },
  { path: '/about', name: 'About', icon: InfoCircleOutlined },
]

const route = useRoute()
const router = useRouter()

const selectedKeys = computed(() => [route.path])

const antMenuItems = computed(() =>
  menuItems.map((item) => ({
    key: item.path,
    label: item.name,
    icon: item.icon ? h(item.icon) : undefined,
  })),
)

const handleMenuClick = ({ key }: { key: string }) => {
  router.push(key)
}
</script>

<template>
  <div class="global-header">
    <div class="header-left">
      <img :src="logoSvg" alt="Logo" class="header-logo" />
      <span class="header-title">AI零代码应用生成平台</span>
    </div>
    <div class="header-center">
      <a-menu
        mode="horizontal"
        theme="light"
        :selected-keys="selectedKeys"
        :items="antMenuItems"
        @click="handleMenuClick"
        class="header-menu"
      />
    </div>
    <div class="header-right">
      <a-button type="primary">登录</a-button>
    </div>
  </div>
</template>

<style scoped>
.global-header {
  display: flex;
  align-items: center;
  height: var(--header-height);
  padding: 0 24px;
  background: var(--header-background);
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
}

.header-left {
  display: flex;
  align-items: center;
  flex-shrink: 0;
}

.header-logo {
  height: 32px;
  width: 32px;
  margin-right: 12px;
}

.header-title {
  font-size: 18px;
  font-weight: 600;
  color: var(--color-heading);
  white-space: nowrap;
}

.header-center {
  flex: 1;
  margin: 0 24px;
}

.header-menu {
  border-bottom: none;
  line-height: var(--header-height);
}

/* 选中项文字高亮 */
.header-menu :deep(.ant-menu-item-selected) {
  color: var(--primary-color);
  font-weight: 600;
}

/* 选中项底部指示条 */
.header-menu :deep(.ant-menu-item-selected)::after {
  border-bottom-color: var(--primary-color) !important;
}

.header-right {
  flex-shrink: 0;
}
</style>
