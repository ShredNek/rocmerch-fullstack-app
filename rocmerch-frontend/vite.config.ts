import { defineConfig } from 'vite'
import path from 'path'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '/@': path.resolve(__dirname, './src'),
    },
  },
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
  assetsInclude: ['**/*.mov', '**/*.jpg'],
  build: {
    target: 'esnext',
  },
  appType: 'mpa',
})
