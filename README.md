# Restaurante — Controle de lotação

App Android que controla quantas pessoas estão dentro de um restaurante.

- **Entrar** soma uma pessoa e **Sair** tira uma.
- Mostra **"Pode entrar"** em verde enquanto há lugares livres e **"Lotado"** em vermelho quando a capacidade (20 lugares) é atingida.
- Barra de ocupação animada, vibração leve ao tocar e contagem preservada ao girar a tela.
- Os botões são desativados quando não fazem sentido (não passa da capacidade nem fica negativo).

## Tecnologias

Kotlin · Android Views (XML) · Material 3 · minSdk 24 / targetSdk 36

## Como rodar

Abra o projeto no Android Studio e clique em **Run**, ou pelo terminal:

```bash
./gradlew assembleDebug        # gera o APK
./gradlew testDebugUnitTest    # roda os testes
```

A capacidade pode ser alterada em `CAPACIDADE_MAXIMA` no `MainActivity.kt`.
