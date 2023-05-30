using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class gameManager : MonoBehaviour
{
    public GameObject rain;
    public Text scoreText;
    public Text timeText;
    public GameObject panel;
    public GameObject rtan;

    public static gameManager I;

    int totalScore = 0;

    float limit = 5.0f;
    // Start is called before the first frame update

    void Awake()
    {
        I = this;
    }

    void Start()
    {
        InvokeRepeating("makeRain",0.0f,0.5f);
        initGame();
    }
    
    void initGame()
    {
        Time.timeScale = 1.0f;
        limit = 5.0f;
        totalScore = 0;
    }

    void makeRain()
    {
        Instantiate(rain);
    }

    // Update is called once per frame
    void Update()
    {
        limit -= Time.deltaTime;
        timeText.text = limit.ToString("N2");
        if(limit<0)
        {
            limit = 0.0f;
            panel.SetActive(true);
            Time.timeScale = 0.0f;
            rtan.SetActive(false);
        }
    }

    public void addScore(int score)
    {
        totalScore += score;
        scoreText.text = totalScore.ToString();
    }

    public void retry()
    {
        SceneManager.LoadScene("빗방울모으기");
    }
}
