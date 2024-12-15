import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter, Gauge } from 'k6/metrics';

export let options = {
  vus: 10,
  duration: '30s',
};

const successfulRequests = new Counter('successful_requests');
const failedRequests = new Counter('failed_requests');
const responseTimes = new Gauge('response_times');

export default function () {
  const quantidade = Math.floor(Math.random() * 5) + 1;
  const url = `http://localhost:8080/pedido/novo_pessimista?produtoId=1&quantidade=${quantidade}`;
  
  const res = http.post(url);
  responseTimes.add(res.timings.duration);

  if (res.status === 200) {
    successfulRequests.add(1);
  } else if (res.status === 409) {
    failedRequests.add(1);
  }

  check(res, { 'status was 200': (r) => r.status === 200 });
  sleep(1);
}
