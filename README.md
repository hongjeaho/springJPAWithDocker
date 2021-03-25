
# 1. istio 설치 (준비단계)

## 1-1. istioctl 최신 버전을 다운로드 한다.
```
curl -L https://istio.io/downloadIstio | sh -
```
## 1-2. istioctl path 등록
.bash_prifile에 istioctl/bin을 등록한다.

## 1-3. istio 설치
```
istioctl install --set profile=demo
```

## 1-4. kiali
### 설치
```
helm install \
  --namespace istio-system \
  --set auth.strategy="anonymous" \
  --repo https://kiali.org/helm-charts \
  kiali-server \
  kiali-server
```

### 삭제
```
helm uninstall --namespace istio-system kiali-server
kubectl delete crd monitoringdashboards.monitoring.kiali.io
```

## 1-4. Prometheus 설치
```
kubectl appy -f {ISTIO_HOME}/sample/addons/prometheus
```

## 1-5. kiali 실행
```
istioctl dashboard kiali
```


# 2. istio sidecar 활성화

## 2-1. demo namespace 생성한다.
```
kubectl create namespace demo
```

## 2-2. istio injection  활성화 
```
kubectl label namespace demo istio-injection=enabled
```
## 3. 각 네임스페이스별로 활성화 여부 확인 
```
kubectl get namespace -L istio-injection --show-labels
```
