using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Moveone : MonoBehaviour {

    public GameObject shoot;
    public GameObject shootPosition1;
    public GameObject shootPosition2;

    public Image[] images;

    private Rigidbody ball1;
    private Rigidbody ball2;

    private int num;
   

    // Use this for initialization
    void Start () {
        num = 3;
	}
	
	// Update is called once per frame
	void Update () {

        if (Input.GetKey(KeyCode.A))
        {
            transform.position += new Vector3(-0.1f, 0, 0);
        }
        if (Input.GetKey(KeyCode.D))
        {
            transform.position += new Vector3(0.1f, 0, 0);
        }
        if (Input.GetKey(KeyCode.W))
        {
            transform.position += new Vector3(0, 0, 0.1f);
        }
        if (Input.GetKey(KeyCode.S))
        {
            transform.position += new Vector3(0, 0, -0.1f);
        }


        if (Input.GetKeyDown(KeyCode.Space))
        {
            ball1 = Instantiate(shoot, shootPosition1.transform.position, Quaternion.identity).GetComponent<Rigidbody>();
            ball1.AddForce(new Vector3(20f, 3f, 0), ForceMode.Impulse);

            ball2 = Instantiate(shoot, shootPosition2.transform.position, Quaternion.identity).GetComponent<Rigidbody>();
            ball2.AddForce(new Vector3(-20f, 3f, 0), ForceMode.Impulse);

        }

    }
    private void OnCollisionEnter(Collision collision)//부딪히면 실행됨. 파라미터는 부딪힌 대상이 들어옴
    {

        if (collision.transform.tag == "Ball")
        {
            if (num > 0)
            {
                num--;
                Destroy(images[num]);
                if(num == 0)
                {
                    gameObject.GetComponent<Renderer>().enabled = false;
                    gameObject.transform.position = new Vector3(6, 0, 6);
                    Destroy(gameObject,0.1f);
                }
            }
        }

    }
}
