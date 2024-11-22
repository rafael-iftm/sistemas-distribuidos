let socket = new WebSocket("ws://localhost:8080/ws"); // Conecta ao servidor WebSocket

// Área de texto para exibir mensagens
const messagesArea = document.getElementById("messages");

// Evento: Conexão aberta
socket.onopen = () => {
    messagesArea.value += "Conexão aberta.\n";
};

// Evento: Mensagem recebida
socket.onmessage = (event) => {
    messagesArea.value += "Mensagem do servidor: " + event.data + "\n";
};

// Evento: Conexão fechada
socket.onclose = () => {
    messagesArea.value += "Conexão fechada.\n";
};

// Função para enviar mensagem ao servidor
function sendMessage() {
    const input = document.getElementById("messageInput").value;
    socket.send(input);
    messagesArea.value += "Você: " + input + "\n";
    document.getElementById("messageInput").value = ""; // Limpa o campo de entrada
}
