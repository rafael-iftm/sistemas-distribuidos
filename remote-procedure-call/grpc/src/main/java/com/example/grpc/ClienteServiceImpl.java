package com.example.grpc;

import com.example.grpc.ClienteServiceProto.ClienteRequest;
import com.example.grpc.ClienteServiceProto.ClienteResponse;

import io.grpc.stub.StreamObserver;

public class ClienteServiceImpl extends ClienteServiceGrpc.ClienteServiceImplBase {
    @Override
    public void obterCliente(ClienteRequest request, StreamObserver<ClienteResponse> responseObserver) {
        // Simulação de busca no banco de dados
        ClienteResponse response = ClienteResponse.newBuilder()
            .setId(request.getId())
            .setNome("Cliente Exemplo")
            .setEmail("exemplo@iftm.edu.br")
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
