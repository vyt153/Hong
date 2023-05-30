def solution(common):
    answer = 0
    i = 0
    if common[i+1]-common[i] == common[i+2]-common[i+1]:
        answer += common[-1]+(common[i+1]-common[i])
    elif common[i+1]/common[i] == common[i+2]/common[i+1]:
        answer += common[-1]*(common[i+1]/common[i])
    return answer