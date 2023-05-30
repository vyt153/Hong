def solution(A,B):
    answer = 0
    B = B*2
    answer += B.find(A)
    return answer