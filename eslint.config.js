import { FlatCompat } from '@eslint/eslintrc';
import js from '@eslint/js';

const compat = new FlatCompat({
    baseDirectory: import.meta.url, // ES6 modülleri kullanımı için
});

export default [
    js.configs.recommended, // ESLint önerilen ayarları
    {
        files: ['**/*.js'], // JavaScript dosyaları için geçerli
        languageOptions: {
            ecmaVersion: 'latest',
            sourceType: 'module',
        },
        rules: {
            'no-unused-vars': 'off', // Kullanılmayan değişken uyarısını devre dışı bırak
        },
    },
];
