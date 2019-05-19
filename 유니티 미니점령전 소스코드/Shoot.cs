using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Shoot : MonoBehaviour {

    // Use this for initialization
    void Start () {
        Destroy(gameObject, 2f);
    }
	
	// Update is called once per frame
	void Update () {
		
	}

    private void OnCollisionEnter(Collision collision)//부딪히면 실행됨. 파라미터는 부딪힌 대상이 들어옴
    {
        Destroy(gameObject);

    }
}
