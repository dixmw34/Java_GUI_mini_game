using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class Game : MonoBehaviour {

    public bool red = false;
    public bool blue = false;

    public float num1 = 0.0f;
    public float num2 = 0.0f;

    public GameObject cube;
    public Image img;

    public Text winText;

    // Use this for initialization
    void Start () {
        //Destroy(gameObject, 3f);
    }
	
	// Update is called once per frame
	void Update () {
        if(red == false && blue == false)
        {
            if(num1<1.0f && num2 < 1.0f)
            {
                //Debug.Log("check1");
                //cube.GetComponent<Renderer>().material.color = Color.white;
                if (num1 > 0.0f)
                {
                    num1 -= 0.1f * Time.deltaTime;
                    img.fillAmount = num1;
                }
                else if(num2 > 0.0f)
                {
                    num2 -= 0.1f * Time.deltaTime;
                    img.fillAmount = num2;
                }

                if (num1 < 0.0f) num1 = 0.0f;
                if (num2 < 0.0f) num2 = 0.0f;
            }
            
        }
        else if (red == true && blue == false)
        {
    
            if (num2 < 1.0f)
            {
                cube.GetComponent<Renderer>().material.color = Color.red;
                img.color = Color.red;
                num2 = 0.0f;
                if (num1 < 1.0f) num1 += 0.5f * Time.deltaTime;
                else if (num1 > 1.0f) num1 = 1.0f;
                img.fillAmount = num1;
            }
            
        }
        else if (red == false && blue == true)
        {
            if (num1 < 1.0f)
            {
                cube.GetComponent<Renderer>().material.color = Color.blue;
                img.color = Color.blue;
                num1 = 0.0f;
                if (num2 < 1.0f) num2 += 0.5f * Time.deltaTime;
                else if (num2 > 1.0f) num2 = 1.0f;
                img.fillAmount = num2;
            }
            
        }
        else
        {
            if (num1 < 1.0f && num2 < 1.0f)
            {
                cube.GetComponent<Renderer>().material.color = Color.magenta;
            }
                
        }

        if(num1 == 1.0f)
        {
            winText.text = "Red Win!!!";
        }
        else if(num2 == 1.0f)
        {
            winText.text = "Blue Win!!!";
        }

        if (Input.GetKeyDown(KeyCode.Escape))

        {
            Application.Quit();

        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if(other.tag == "Player1")
        {
            red = true;
        }
        if (other.tag == "Player2")
        {
            blue = true;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.tag == "Player1")
        {
            red = false;
        }
        if (other.tag == "Player2")
        {
            blue = false;
        }
    }
}
