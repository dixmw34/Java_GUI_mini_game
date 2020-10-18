using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerBall : MonoBehaviour
{
    public float jumpPower = 30;
    public int Itemcnt = 0;
    public GameManagerLogic manager;
    bool is_Jump;
    Rigidbody rigid;
    AudioSource audio;

    void Awake()
    {
        is_Jump = false;
        rigid = GetComponent<Rigidbody>();
        audio = GetComponent<AudioSource>();
    }

    void Update()
    {
        if (Input.GetButtonDown("Jump") && !is_Jump)
        {
            is_Jump = true;
            rigid.AddForce(new Vector3(0, jumpPower, 0), ForceMode.Impulse);
        }
    }

    void FixedUpdate()
    {
        float h = Input.GetAxisRaw("Horizontal")*0.7f;
        float v = Input.GetAxisRaw("Vertical")*0.7f;

        rigid.AddForce(new Vector3(h, 0, v), ForceMode.Impulse);

    }

    void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.tag == "Floor") is_Jump = false;

    }

    void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Coin")
        {
            Itemcnt++;
            audio.Play();
            other.gameObject.SetActive(false);
            manager.GetItem(Itemcnt);

        }

        else if(other.tag == "Finish")
        {
            if(manager.TotalItemCount == Itemcnt)
            {
                //Game Clear!
                SceneManager.LoadScene(manager.stage + 1);
            }
            else
            {
                //Restart..
                SceneManager.LoadScene(manager.stage);
            }

        }
    }
}
