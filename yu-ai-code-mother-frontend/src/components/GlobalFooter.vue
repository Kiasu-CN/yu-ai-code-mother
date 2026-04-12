<template>
  <div class="global-footer">
    <span class="footer-text">
      编程导航原创项目 by
      <a href="https://www.codefather.cn" target="_blank" rel="noopener noreferrer">
        程序员鱼皮
      </a>
    </span>
  </div>
</template>

<style scoped>
.global-footer {
  text-align: center;
  padding: 12px 24px;
  background: var(--footer-background);
  color: var(--footer-text-color);
  font-size: 14px;
}

.footer-text a {
  color: var(--primary-color);
  text-decoration: none;
}

.footer-text a:hover {
  text-decoration: underline;
}
</style>
