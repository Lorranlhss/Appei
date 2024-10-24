# Bioma Amazônico - Aplicativo Educativo

Este projeto é um aplicativo educativo focado em ensinar sobre o bioma da Amazônia, sua fauna, flora, e os desafios enfrentados para sua preservação. Além disso, o aplicativo inclui um quiz interativo que testa o conhecimento do usuário sobre o tema.

## Funcionalidades

- **Mapa Interativo**: Exibe a localização da Amazônia e destaca áreas importantes e ameaçadas.
- **Fauna e Flora**: Lista detalhada das espécies mais importantes da Amazônia, com descrições de cada uma.
- **Quiz Educativo**: Um quiz de múltipla escolha que testa o conhecimento dos usuários sobre o bioma.
- **Conexão com Firebase**: Armazenamento em tempo real de dados de fauna, flora e perguntas do quiz, utilizando o Firebase Realtime Database.

## Tecnologias Utilizadas

- **Android Studio**: Ambiente de desenvolvimento para criação do aplicativo mobile.
- **Firebase Realtime Database**: Utilizado para armazenar e recuperar informações em tempo real.
- **Google Maps API**: Exibe o mapa interativo da Amazônia.

## Estrutura do Projeto

- **FaunaFloraActivity**: Tela responsável por exibir uma lista de espécies da fauna e flora da Amazônia, utilizando o Firebase para recuperar os dados.
- **QuizActivity**: Tela que exibe o quiz interativo. As perguntas e respostas são carregadas do Firebase, e o usuário pode avançar entre as perguntas.
- **MainActivity**: Tela inicial com navegação para as diferentes funcionalidades do app, incluindo o mapa, fauna, flora e quiz.

## Como Executar

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/seuusuario/bioma-amazonico-app.git
    ```

2. **Abra o projeto no Android Studio**:
    - Selecione a pasta do projeto e abra no Android Studio.

3. **Configurar Firebase**:
    - Adicione o arquivo `google-services.json` do Firebase na pasta `app/`.
    - Certifique-se de que as permissões de leitura e escrita no Realtime Database estão configuradas.

4. **Execute o aplicativo**:
    - Conecte um dispositivo Android ou use um emulador.
    - Clique em "Run" no Android Studio para compilar e executar o app.

## Estrutura de Dados no Firebase

```json
{
  "faunaFlora": {
    "1": {
      "nome": "Onça-Pintada",
      "tipo": "Animal",
      "descricao": "Maior felino da América, encontrado na Floresta Amazônica."
    },
    "2": {
      "nome": "Samaúma",
      "tipo": "Planta",
      "descricao": "Árvore gigante, conhecida como a 'Rainha da Floresta'."
    }
  },
  "quiz": {
    "perguntas": {
      "1": {
        "pergunta": "Qual o maior felino da América?",
        "opcoes": [
          "Onça-Pintada",
          "Tigre",
          "Leopardo"
        ],
        "respostaCorreta": "Onça-Pintada"
      },
      "2": {
        "pergunta": "Qual árvore é conhecida como a 'Rainha da Floresta'?",
        "opcoes": [
          "Samaúma",
          "Copaíba",
          "Castanheira"
        ],
        "respostaCorreta": "Samaúma"
      }
    }
  }
}
