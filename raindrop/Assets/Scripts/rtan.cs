using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class rtan : MonoBehaviour
{
    float direction = 0.02f;
    float toward = 1.0f;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if(transform.position.x > 2.8f)
        {
            direction = -0.02f;
            toward = -1.0f;
        }
        if(transform.position.x < -2.8f)
        {
            direction = +0.02f;
            toward = 1.0f;
        }
        if(Input.GetMouseButtonDown(0))
        {
            direction *= -1;
            toward *= -1;
        }
        transform.position += new Vector3(direction,0.0f,0.0f);
        transform.localScale = new Vector3(toward, 1, 1);
        
    }
}
