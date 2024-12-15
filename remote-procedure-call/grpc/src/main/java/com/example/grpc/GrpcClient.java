package com.example.grpc;

import com.example.grpc.ClienteServiceProto.ClienteRequest;
import com.example.grpc.ClienteServiceProto.ClienteResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 50051)
            .usePlaintext()
            .build();

        ClienteServiceGrpc.ClienteServiceBlockingStub stub = ClienteServiceGrpc.newBlockingStub(channel);

        ClienteRequest request = ClienteRequest.newBuilder()
            .setId(1)
            .build();

        ClienteResponse response = stub.obterCliente(request);
        System.out.println("Cliente recebido:");
        System.out.println("ID: " + response.getId());
        System.out.println("Nome: " + response.getNome());
        System.out.println("Email: " + response.getEmail());

        channel.shutdown();
    }
}
