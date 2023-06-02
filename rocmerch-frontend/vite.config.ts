import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue({
      template: {
        compilerOptions: {
          isCustomElement: (tag) => {
            return tag.startsWith('ion-') // (return true)
          },
        },
      },
    }),
  ],
  optimizeDeps: {
    include: ['@ffmpeg/ffmpeg', 'src/assets/videos/VisualiserEdited_D1.mov'],
  },
  assetsInclude: ['**/*.mov'],
  build: {
    target: 'esnext',
  },
})
