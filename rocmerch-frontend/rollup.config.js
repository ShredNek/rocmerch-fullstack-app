import { importMetaAssets } from '@web/rollup-plugin-import-meta-assets'

export default {
  input: 'src/main.ts',
  output: {
    dir: 'output',
    format: 'es',
  },
  plugins: [importMetaAssets()],
}
