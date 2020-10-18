using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameManagerLogic : MonoBehaviour
{ 
    public int TotalItemCount = 4;
    public int stage;
    public Text stageText;
    public Text playerText;

    void Awake()
    {
        stageText.text = "/ " + TotalItemCount;
    }

    public void GetItem(int count)
    {
        playerText.text = count.ToString();
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            SceneManager.LoadScene(stage);
        }
    }

}
