def solution(babbling):
    answer = 0
    a = ["aya", "ye", "woo", "ma"]
    for i in range(0, len(babbling)):
        pre_answer = 0        
        for j in range(0, len(a)):
            if a[j] in babbling[i]:
                if j == 0 or j == 2:
                    pre_answer += 3
                elif j == 1 or j == 3:
                    pre_answer += 2
        if pre_answer == len(babbling[i]):
            answer += 1
    return answer