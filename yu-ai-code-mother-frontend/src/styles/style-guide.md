# 样式规范

本规范用于保证项目页面样式的统一性。所有 CSS 变量定义在 `src/assets/base.css` 中。

---

## 颜色体系

使用 CSS 自定义属性（Custom Properties）管理颜色，确保全局一致性。

| 变量 | 值 | 用途 |
|------|----|------|
| `--primary-color` | `#1677ff` | 主色（按钮、链接、强调） |
| `--layout-background` | `#f0f2f5` | 页面整体背景 |
| `--header-background` | `#fff` | 顶部导航栏背景 |
| `--footer-background` | `#f5f5f5` | 底部版权栏背景 |
| `--footer-text-color` | `rgba(0, 0, 0, 0.45)` | 版权栏文字 |
| `--color-heading` | `#2c3e50` | 标题文字 |
| `--color-text` | `#2c3e50` | 正文文字 |
| `--color-border` | `rgba(60, 60, 60, 0.12)` | 边框 |

**原则**：不硬编码颜色值，始终使用 CSS 变量引用。如需新颜色，先在 `base.css` 中定义变量。

---

## 字体排版

- **字体族**：Inter, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif
- **基础字号**：15px
- **行高**：1.6
- **标题字重**：600
- **正文字重**：400（normal）

| 层级 | 字号 | 字重 | 场景 |
|------|------|------|------|
| H1 | 28px | 600 | 页面主标题 |
| H2 | 24px | 600 | 区域标题 |
| H3 | 20px | 600 | 卡片/模块标题 |
| 正文 | 16px | 400 | 段落文字 |
| 辅助 | 14px | 400 | 说明、备注文字 |

---

## 间距规范

采用 **4px 倍数** 体系：

| 值 | 用途 |
|----|------|
| 4px | 紧凑间距（图标与文字间） |
| 8px | 元素内部间距 |
| 12px | 小间距 |
| 16px | 模块内间距 |
| 24px | 模块间间距（标准） |
| 32px | 区域间间距 |
| 48px | 页面级大间距 |

布局级间距使用 CSS 变量：
- `--content-padding: 24px`（内容区域内边距）
- `--content-max-width: 1200px`（内容区最大宽度）

---

## 响应式断点

| 断点 | 宽度 | 设备 |
|------|------|------|
| `xs` | < 576px | 小屏手机 |
| `sm` | >= 576px | 手机 |
| `md` | >= 768px | 平板竖屏 |
| `lg` | >= 992px | 平板横屏/小桌面 |
| `xl` | >= 1200px | 桌面 |
| `xxl` | >= 1600px | 大桌面 |

内容区在 `xl` 以上居中（max-width: 1200px），以下自适应全宽。

---

## 组件样式模式

1. **始终使用 `<style scoped>`**：防止样式污染其他组件
2. **使用 CSS 变量**：跨组件共享的值（颜色、间距）使用 `var(--xxx)` 引用
3. **避免深度选择器**：不要用 `:deep()` 覆盖 Ant Design 组件内部样式，除非必要
4. **BEM 命名替代方案**：使用语义化的单层 class 名（如 `.home-view`、`.header-title`），scoped 已保证隔离

---

## 布局模板

新页面只需关注内容区域，布局已由 `BasicLayout` 提供：

```vue
<template>
  <div class="page-name">
    <!-- 页面内容 -->
  </div>
</template>

<style scoped>
.page-name {
  /* 页面级样式 */
}
</style>
```

内容自动限制在 1200px 最大宽度内居中。

---

## 文件命名约定

| 类型 | 规则 | 示例 |
|------|------|------|
| 页面视图 | PascalCase + View 后缀 | `HomeView.vue`、`UserProfileView.vue` |
| 布局组件 | PascalCase | `BasicLayout.vue` |
| 全局组件 | PascalCase + Global 前缀 | `GlobalHeader.vue` |
| 业务组件 | PascalCase | `UserCard.vue` |
| CSS class | kebab-case | `.page-name`、`.header-title` |
| CSS 变量 | kebab-case + 双横线 | `--primary-color` |
