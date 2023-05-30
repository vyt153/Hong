def solution(chicken):
    answer = 0
    for i in range(0, len(str(chicken))):
        pre_answer = chicken//10
        answer += pre_answer
        chicken = chicken%10 + pre_answer
    return answer